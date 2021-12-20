package alex.falendysh.epam_exhibition.util.enums;

public enum TicketType {
    FULL(1.0),
    STUDENT(0.6),
    KID(0.5),
    PENSIONER(0.7),
    EMPLOYEE(0.4),
    FREE(0.01);

    private double rate;

    TicketType(double v) {
        this.rate = v;
    }

    public double getPriceRate() {
        return rate;
    }
}