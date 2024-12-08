import java.util.List;

public class Car {
    private String name;
    private List<Variant> variants;

    public Car(String name, List<Variant> variants) {
        this.name = name;
        this.variants = variants;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public double getVariantPrice(int variantIndex) {
        return variants.get(variantIndex).getPrice();
    }
    //variants.get(variantIndex) gives the variant object and we use the function of that
}
