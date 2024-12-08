import java.util.*;

public class CarPurchase {
    public static void main(String[] args) {
         Map<String, Car> cars = new HashMap<>();
         Map<String, Accessories> accessories = new HashMap<>();
        cars.put("Alto K10",new Car("Alto k10",List.of(new Variant("STD",3.99),new Variant("LXI",4.25),new Variant("VXI",5.00),new Variant("VXI+",5.96))));
        cars.put("Wagon R",new Car("Wagon R",List.of(new Variant("LXI",5.54),new Variant("VXI",6.25),new Variant("ZXI",6.75),new Variant("ZXI+",7.00))));

        accessories.put("Exterior Accessories",new Accessories("Exterior Accessories",Map.of("Alloy wheels","₹15,000 - ₹40,000","Body Side Moulding","₹1,500 - ₹3,000","DoorVisors","₹1,000 - ₹2,500","Mud Flaps","₹76,000 - ₹78,200")));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your budget (in Lakhs): ₹");
        double budget = scanner.nextDouble();

        // Step 1: Display available cars within the budget
        System.out.println("\nAvailable cars within your budget:");
        List<String> availableCars = new ArrayList<>();
        for (String carName : cars.keySet()) {
            Car car = cars.get(carName);
            double minPrice = car.getVariantPrice(0); // min price of the car (first variant)
            if (minPrice <= budget) {
                availableCars.add(carName);
                System.out.println(carName + " (Min Price: ₹" + minPrice + " Lakhs)");
            }
        }

        if (availableCars.isEmpty()) {
            System.out.println("Sorry, no cars available within your budget.");
            return;
        }

        // Step 2: User selects a car
        System.out.print("\nSelect a car by entering its name: ");
        scanner.nextLine();
        String selectedCar = scanner.nextLine();

        if (!availableCars.contains(selectedCar)) {
            System.out.println("Invalid car selection.");
            return;
        }


        // Step 3: Display car variants and prices, only within the budget
        Car car = cars.get(selectedCar);
        System.out.println("\nAvailable variants for " + selectedCar + " within your budget:");
        List<Variant> variants = car.getVariants();
        List<Variant> affordableVariants = new ArrayList<>();

        // Filter variants that are within the budget
        for (Variant variant : variants) {
            if (variant.getPrice() <= budget) {
                affordableVariants.add(variant);
                System.out.println(variant.getName() + " - ₹" + variant.getPrice());
            }
        }

        if (affordableVariants.isEmpty()) {
            System.out.println("No variants available within your budget.");
            return;
        }

        // Step 4: Let user select a variant within their budget
        double basePrice=0.00;
        String selectedVariant = "";
        boolean validSelection = false;
        while (!validSelection)
        {
            System.out.print("\nSelect a variant (enter the variant name): ");
            selectedVariant = scanner.nextLine();

            // Check if the selected variant is in the affordable variants list
            for (Variant variant : affordableVariants) {
                if (variant.getName().equals(selectedVariant))
                {
                    basePrice=variant.getPrice();
                    validSelection = true;
                    break;
                }
            }

            if (!validSelection) {
                System.out.println("Invalid variant selection. Please select a variant within your budget.");
            }
        }

        // Calculate remaining budget after selecting the variant
        double remainingBudget = budget - basePrice;
        System.out.println("\nRemaining budget after selecting " + selectedVariant + " variant: ₹" + remainingBudget +"\n");

        // Step 5: Show only affordable accessories
        System.out.println("\nAvailable accessories within your remaining budget:");
        boolean foundAffordableAccessories = false;
        for (String category : accessories.keySet()) {
            Accessories accessory = accessories.get(category);
            System.out.println("\n" + category + ":");
            boolean categoryHasAffordableItems = false;

            for (Map.Entry<String, String> entry : accessory.getItems().entrySet()) {
                String item = entry.getKey();
                String priceRange = entry.getValue();

                // Parse the price range
                String[] prices = priceRange.replace("₹", "").split(" - ");
                int minPrice = Integer.parseInt(prices[0].replace(",", "").trim());  // Get minimum price
                int maxPrice = Integer.parseInt(prices[1].replace(",", "").trim());  // Get maximum price

                // Check if the accessory's minimum price is within the remaining budget
                if (minPrice <= remainingBudget*100000) {
                    System.out.println(item + " - " + priceRange);
                    categoryHasAffordableItems = true;
                }
            }

            if (categoryHasAffordableItems) {
                foundAffordableAccessories = true;
            }
        }

        if (!foundAffordableAccessories) {
            System.out.println("No accessories available within your remaining budget.");
            return;
        }

        // Step 6: User selects accessories
        System.out.print("\nEnter the total cost of accessories you want to add (in Rupees): ₹");
        double accessoriesCost = scanner.nextDouble();

        if (accessoriesCost > remainingBudget*100000) {
            System.out.println("You do not have enough budget for the selected accessories.");
            return;
        }

        // Step 7: Calculate final on-road price
        double registrationFee = 0.07 * basePrice * 100000;  // Convert Lakhs to Rupees
        double insuranceFee = 0.12 * basePrice * 100000;
        double handlingFee = 0.02 * basePrice * 100000;

        double onRoadPrice = basePrice * 100000 + registrationFee + insuranceFee + handlingFee + accessoriesCost;

        System.out.println("\nFinal On-Road Price for " + selectedCar + " (" + selectedVariant + "):");
        System.out.println("Base Price: ₹" + basePrice * 100000);
        System.out.println("Registration Fee: ₹" + registrationFee);
        System.out.println("Insurance Fee: ₹" + insuranceFee);
        System.out.println("Handling Fee: ₹" + handlingFee);
        System.out.println("Accessories Cost: ₹" + accessoriesCost);
        System.out.println("Final On-Road Price: ₹" + onRoadPrice);

        if(onRoadPrice > budget) {
            System.out.println("Your budget is not sUfficeint for this car and accessories selection");
        }
    }
}