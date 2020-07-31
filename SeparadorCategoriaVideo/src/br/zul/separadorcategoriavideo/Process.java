package br.zul.separadorcategoriavideo;

import br.zul.zwork4.io.ZFile;
import br.zul.zwork4.io.txt.ZTxtFileReader;
import br.zul.zwork4.io.txt.ZTxtFileWriter;
import br.zul.zwork4.json.ZJsonArray;
import br.zul.zwork4.json.ZJsonObject;
import br.zul.zwork4.value.ZValue;
import java.io.IOException;

/**
 *
 * @author luizh
 */
public class Process {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private ZFile in;
    private ZFile out;
    private boolean replaceOut;
    
    //==========================================================================
    //CONSTRUTORES
    //==========================================================================
    public Process(ZFile in, ZFile out, boolean replaceOut) {
        this.in = in;
        this.out = out;
        this.replaceOut = replaceOut;
    }
    
    //==========================================================================
    //MÉTODOS PÚBLICOS
    //==========================================================================
    public void process() throws IOException{
        validateReplace();
        ProcessCtx ctx = new ProcessCtx();
        readIn(ctx);
        fillCategorias(ctx);
        fillVideos(ctx);
        saveJSON(ctx);
    }
    
    //==========================================================================
    //MÉTODOS PRIVADOS
    //==========================================================================
    private void validateReplace() throws IOException {
        if (out.exists()&&!replaceOut){
            throw new IOException("O arquivo de saída já existe.");
        }
    }

    private void readIn(ProcessCtx ctx) {
        String content = new ZTxtFileReader(in).readAll();
        ctx.setIn(new ZJsonObject(content));
    }

    private void fillCategorias(ProcessCtx ctx) {
        ZJsonArray categorias = new ZJsonArray(ctx.getIn().get("categorias").asString());
        for (int i=0;i<categorias.size();i++){
            ZJsonObject categoria = categorias.get(i).asJsonObject();
            categoria.put("id", i+1);
            categoria.remove("videos");
            categorias.put(i, categoria);
        }
        ctx.setCategorias(categorias);
    }

    private void fillVideos(ProcessCtx ctx) {
        int lastVideoId = 0;
        int lastCategoriaId = 0;
        
        ZJsonArray videos = new ZJsonArray();
        ZJsonArray categorias = ctx.getIn().get("categorias").asJsonArray();
        for (ZValue categoriaRaw: categorias.listValues()){
            
            ZJsonObject categoria = categoriaRaw.asJsonObject();
            lastCategoriaId ++;
            
            for (ZValue videoRaw: categoria.get("videos").asJsonArray().listValues()){
                ZJsonObject video = videoRaw.asJsonObject();
                video.put("id", ++lastVideoId);
                video.put("categoriaId", lastCategoriaId);
                videos.add(video);
            }
            
        }
        ctx.setVideos(videos);
    }

    private void saveJSON(ProcessCtx ctx) {
        ZJsonObject jsonObject = new ZJsonObject();
        jsonObject.put("categorias", ctx.getCategorias());
        jsonObject.put("videos", ctx.getVideos());
        new ZTxtFileWriter(out, false).writeAll(jsonObject.toBeautifulString());
    }
    
}
