package entity;

public class Car extends Entity {
    private int year;
    private int makeId;
    private String model;
    private String registrationNumber;
    private int mileage;
    private int gearboxType;
    private float pricePerDay;
    private float PricePerDay2;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(int gearboxType) {
        this.gearboxType = gearboxType;
    }

    public float getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public float getPricePerDay2() {
        return PricePerDay2;
    }

    public void setPricePerDay2(float pricePerDay2) {
        PricePerDay2 = pricePerDay2;
    }
}
