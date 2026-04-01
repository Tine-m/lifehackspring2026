package app.controllers.teamI;
import app.entities.teamI.CoffeeFavorits;
import app.persistence.ConnectionPool;
import app.persistence.teamI.CoffeeMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.awt.*;
import java.util.Map;

public class CoffeController {


    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {

        app.post("/teamI_copi", ctx -> {
            Integer userId = ctx.sessionAttribute("user_id");
            if (userId == null) {
                ctx.status(401).result("Du skal logge ind først!");
                return;
            }
            String coffeetype = ctx.formParam("coffeetype");
            CoffeeMapper coffeeMapper = new CoffeeMapper(connectionPool);
            boolean success = coffeeMapper.addCoffeTypeToFavorit(coffeetype, userId);
            if (success) {
                ctx.result("Kaffen blev tilføjet til dine favoritter!");
            } else {
                ctx.result("Noget gik galt, prøv igen.");
            }
        });

        app.get("/teamI/favorit", ctx->{
            CoffeeMapper mapper = new CoffeeMapper(connectionPool);
            Integer userId = ctx.sessionAttribute("user_id");
            List<CoffeeFavorits> favoritList = mapper.getFavorit(userId);


            ctx.render("/teamI/Favorites.html", Map.of("favoritList", favoritList));

            if (userId == null) {
                ctx.status(401).result("Du skal logge ind først!");
            }
        });
    }

    public static void createCoffee(Context ctx, ConnectionPool connectionPool) {

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
