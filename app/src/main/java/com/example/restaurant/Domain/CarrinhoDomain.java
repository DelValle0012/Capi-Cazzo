package com.example.restaurant.Domain;

import android.os.Parcel;
import android.os.Parcelable;

public class CarrinhoDomain implements Parcelable {
    private String nome;
    private int quantidade;
    private Double valor_total;
    private String foto;

    public CarrinhoDomain(String nome, int quantidade, Double valor_total, String foto) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor_total = valor_total;
        this.foto = foto;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    // Métodos Parcelable
    protected CarrinhoDomain(Parcel in) {
        nome = in.readString();
        quantidade = in.readInt();
        if (in.readByte() == 0) {
            valor_total = null;
        } else {
            valor_total = in.readDouble();
        }
        foto = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeInt(quantidade);
        if (valor_total == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(valor_total);
        }
        dest.writeString(foto);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CarrinhoDomain> CREATOR = new Creator<CarrinhoDomain>() {
        @Override
        public CarrinhoDomain createFromParcel(Parcel in) {
            return new CarrinhoDomain(in);
        }

        @Override
        public CarrinhoDomain[] newArray(int size) {
            return new CarrinhoDomain[size];
        }
    };
}
