/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service.ApiWS_20190140017;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "snack")
@NamedQueries({
    @NamedQuery(name = "Snack.findAll", query = "SELECT s FROM Snack s"),
    @NamedQuery(name = "Snack.findByIdSnack", query = "SELECT s FROM Snack s WHERE s.idSnack = :idSnack"),
    @NamedQuery(name = "Snack.findByNamaSnack", query = "SELECT s FROM Snack s WHERE s.namaSnack = :namaSnack"),
    @NamedQuery(name = "Snack.findByJumlahSnack", query = "SELECT s FROM Snack s WHERE s.jumlahSnack = :jumlahSnack"),
    @NamedQuery(name = "Snack.findByHarga", query = "SELECT s FROM Snack s WHERE s.harga = :harga")})
public class Snack implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_snack")
    private Integer idSnack;
    @Column(name = "nama_snack")
    private String namaSnack;
    @Column(name = "jumlah_snack")
    private Integer jumlahSnack;
    @Column(name = "harga")
    private Integer harga;

    public Snack() {
    }

    public Snack(Integer idSnack) {
        this.idSnack = idSnack;
    }

    public Integer getIdSnack() {
        return idSnack;
    }

    public void setIdSnack(Integer idSnack) {
        this.idSnack = idSnack;
    }

    public String getNamaSnack() {
        return namaSnack;
    }

    public void setNamaSnack(String namaSnack) {
        this.namaSnack = namaSnack;
    }

    public Integer getJumlahSnack() {
        return jumlahSnack;
    }

    public void setJumlahSnack(Integer jumlahSnack) {
        this.jumlahSnack = jumlahSnack;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSnack != null ? idSnack.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Snack)) {
            return false;
        }
        Snack other = (Snack) object;
        if ((this.idSnack == null && other.idSnack != null) || (this.idSnack != null && !this.idSnack.equals(other.idSnack))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.service.ApiWS_20190140017.Snack[ idSnack=" + idSnack + " ]";
    }
    
}
