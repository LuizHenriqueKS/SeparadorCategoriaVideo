package br.zul.separadorcategoriavideo;

import br.zul.zwork4.json.ZJsonArray;
import br.zul.zwork4.json.ZJsonObject;

/**
 *
 * @author luizh
 */
public class ProcessCtx {
    
    //==========================================================================
    //VARIÁVEIS
    //==========================================================================
    private ZJsonObject in;
    private ZJsonArray categorias;
    private ZJsonArray videos;
    
    //==========================================================================
    //GETTERS E SETTERS
    //==========================================================================
    public ZJsonObject getIn() {
        return in;
    }
    public void setIn(ZJsonObject in) {
        this.in = in;
    }

    public ZJsonArray getCategorias() {
        return categorias;
    }
    public void setCategorias(ZJsonArray categorias) {
        this.categorias = categorias;
    }

    public ZJsonArray getVideos() {
        return videos;
    }
    public void setVideos(ZJsonArray videos) {
        this.videos = videos;
    }
    
}
