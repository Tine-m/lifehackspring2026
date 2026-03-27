package app.controllers.teamI;

import app.persistence.ConnectionPool;
import app.persistence.teamI.CoffeeMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class CoffeController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
       // Under construction, it will bee use later, this is just so the program can run for now.
        //app.post("/teamI/index", ctx -> getExistingCoffee(ctx, connectionPool));
        //app.post("/teamI/index", ctx -> createCoffee(ctx, connectionPool));
        //app.get("/teamI/color", ctx -> ctx.json(new Integer[]{255,200,5}));


        app.post("/teamI/slider", ctx -> createCoffee(ctx, connectionPool));
    }


    /*
    Under construction, it will bee use later, this is just so the program can run for now.
    public static void getExistingCoffee(Context ctx, ConnectionPool connectionPool) {
        String beanType = ctx.formParam("");
        String coffeeType = ctx.formParam("");
        int totalVolume = Integer.parseInt(ctx.formParam(""));

        CoffeeMapper coffeeMapper = new CoffeeMapper(connectionPool);
        coffeeMapper.getExistingCoffee(beanType, totalVolume);
    }
     */


    public static void createCoffee(Context ctx, ConnectionPool connectionPool) {

        /* Under construction, it will bee use later, this is just so the program can run for now.
        String beanType = ctx.formParam("");
        int totalVolume = Integer.parseInt(ctx.formParam(""));
        int beanPercentages = Integer.parseInt(ctx.formParam(""));
        int milkPercentages = Integer.parseInt(ctx.formParam(""));
        int waterPercentages = Integer.parseInt(ctx.formParam(""));

        CoffeeMapper coffee = new CoffeeMapper(connectionPool);
        coffee.createCoffee(beanType, totalVolume, beanPercentages, milkPercentages, waterPercentages);


        Color color = coffee.createCoffee(beanType, totalVolume, beanPercentages, milkPercentages, waterPercentages).getColor();
        String rgb = "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
        ctx.result(rgb);
         */

        try {
            int sliderValue = ctx.bodyAsClass(SliderValue.class).getSliderValue();
            CoffeeMapper coffeeMapper = new CoffeeMapper(connectionPool);

            ctx.json("Slider value received: " + sliderValue);
        } catch (Exception e) {
            ctx.status(400).result("Invalid data: " + e.getMessage());
        }
    }

    public static class SliderValue {
        private int sliderValue;

        public int getSliderValue() {
            return sliderValue;
        }

        public void setSliderValue(int sliderValue) {
            this.sliderValue = sliderValue;
        }

        public static void addToFavorites() {
            // your existing code
        }

        public static void getFavorites() {
            // your existing code
        }

    }

    public static void addToFavorites() {

    }

    public static void getFavorites() {

    }
}
