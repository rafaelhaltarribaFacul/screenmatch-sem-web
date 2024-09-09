package br.com.testes.screenmatch.service;

import br.com.testes.screenmatch.model.DadosTraducao;

import java.net.URLEncoder;

public class ConsultaMyMemory {
    public static String obterTraducao(String text) {
        ConverteDados conversor = new ConverteDados();

        ConsumoAPI  consumo = new ConsumoAPI();

        String texto = URLEncoder.encode(text);
        final String langpair = URLEncoder.encode("autodetect|pt-br");

        String url = "https://api.mymemory.translated.net/get?q=" + texto + "&langpair=" + langpair;

        String json = consumo.obterDados(url);

        DadosTraducao traducao = conversor.obterDados(json, DadosTraducao.class);

        return traducao.responseData().translatedText;
    }
}