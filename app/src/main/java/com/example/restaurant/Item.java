package com.example.restaurant;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
    private String nome;
    private double preco_unit;
    private double preco_total;
    private String foto;
    private int quantidade;

    public Item(String nome, double preco, String foto, int quantidade) {
        this.nome = nome;
        this.preco_unit = preco;
        this.foto = foto;
        this.quantidade = quantidade;
        this.preco_total = preco * quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco_unit() {
        return preco_unit;
    }

    public String getFoto() {
        return foto;
    }

    public double getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(double preco_total) {
        this.preco_total = preco_total;
    }

    // Construtor para Parcelable
    protected Item(Parcel in) {
        nome = in.readString();
        preco_unit = in.readDouble();
        preco_total = in.readDouble();
        foto = in.readString();
        quantidade = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeDouble(preco_unit);
        dest.writeDouble(preco_total);
        dest.writeString(foto);
        dest.writeInt(quantidade);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
