package objectOriented.carRental.service;

import objectOriented.carRental.model.CarRental;
import objectOriented.carRental.model.Invoice;

public class RentalService {
    private Double pricePerDay;
    private Double pricePerHour;

    private BrazilTaxService taxService;

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public BrazilTaxService getTaxService() {
        return taxService;
    }

    public void setTaxService(BrazilTaxService taxService) {
        this.taxService = taxService;
    }

    public RentalService(Double pricePerHour, Double pricePerDay, BrazilTaxService taxService) {
        super();
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.taxService = taxService;
    }

    public void processInvoice(CarRental carRental) {
        long startDataInMilliseconds = carRental.getStart().getTime();
        long finishDataInMilliseconds = carRental.getFinish().getTime();
        double dataInHours = (double)((((finishDataInMilliseconds - startDataInMilliseconds) / 1000) / 60) / 60);
        double basicPayment;

        if(dataInHours <= 12) {
            basicPayment = Math.ceil(dataInHours) * pricePerHour;
        } else {
            basicPayment = Math.ceil(dataInHours / 24) * pricePerDay;
        }

        double tax = taxService.tax(basicPayment);
        carRental.setInvoice(new Invoice(basicPayment, tax));
        //criado novo objeto de nota de pagamento associado ao aluguel
    }
}
