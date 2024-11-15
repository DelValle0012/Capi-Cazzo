package com.example.restaurant.Domain;
import com.example.restaurant.databinding.FragmentHomeBinding;

public class PopularesDomain {
    private FragmentHomeBinding binding;

    private String nome;
    private String foto;
    private String descricao;
    private Double preco;
    private int numeroProdSelecionado;

    public PopularesDomain(String nome, String foto, String descricao, Double preco){
        this.nome = nome;
        this.foto = foto;
        this.descricao = descricao;
        this.preco = preco;
    }

    public PopularesDomain(String nome, String foto, String descricao, Double preco, int numeroProdSelecionado){
        this.nome = nome;
        this.foto = foto;
        this.descricao = descricao;
        this.preco = preco;
        this.numeroProdSelecionado = numeroProdSelecionado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getNumeroProdSelecionado() {
        return numeroProdSelecionado;
    }

    public void setNumeroProdSelecionado(int numeroProdSelecionado) {
        this.numeroProdSelecionado = numeroProdSelecionado;
    }
}
