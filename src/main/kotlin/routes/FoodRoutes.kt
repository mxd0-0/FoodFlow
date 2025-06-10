package com.example.routes

import com.example.models.FoodItem
import com.example.repositories.FoodRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route


fun Route.foodRoutes() {
    route("/categories") {
        get {
            call.respond(FoodRepository.getAllCategories())
        }
    }

    route("/menu") {
        get {
            val category = call.request.queryParameters["category"]
            call.respond(FoodRepository.getAllFoodItems(category))
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest, "Invalid ID")

            FoodRepository.getFoodItem(id)?.let { item ->
                call.respond(item)
            } ?: call.respond(HttpStatusCode.NotFound)
        }

        post {
            val item = try {
                call.receive<FoodItem>()
            } catch (e: Exception) {
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    "Invalid food item data: ${e.message}"
                )
            }

            val newItem = FoodRepository.addFoodItem(item)
            call.respond(HttpStatusCode.Created, newItem)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@put call.respond(HttpStatusCode.BadRequest, "Invalid ID")

            val item = try {
                call.receive<FoodItem>()
            } catch (e: Exception) {
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    "Invalid food item data: ${e.message}"
                )
            }

            if (FoodRepository.updateFoodItem(id, item)) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@delete call.respond(HttpStatusCode.BadRequest, "Invalid ID")

            if (FoodRepository.deleteFoodItem(id)) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}