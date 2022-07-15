package br.com.santiago.teste.soccernews.ui.adapter

interface ListernerAdapter<T> {

    /**
     * Click para favoritar
     */
    fun onClick(t: T)

}