package in.co.chicmic.flyoverairways.dataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class CustomerDetails {
    @SerializedName("passengerCode")
    @Expose
    private Integer passengerCode;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("eatsHalal")
    @Expose
    private Boolean eatsHalal;

    public Integer getPassengerCode() {
        return passengerCode;
    }

    public void setPassengerCode(Integer passengerCode) {
        this.passengerCode = passengerCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getEatsHalal() {
        return eatsHalal;
    }

    public void setEatsHalal(Boolean eatsHalal) {
        this.eatsHalal = eatsHalal;
    }
}
