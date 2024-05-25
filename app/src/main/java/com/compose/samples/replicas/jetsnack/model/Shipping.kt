package com.compose.samples.replicas.jetsnack.model

data class ShippingDestination(
    val id: Long,
    val name: String,
    val address: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String
)

object ShippingRepo {
    fun getShippingDestinations() = shippingDestinations

    fun searchShippingDestination(query: String) = shippingDestinations.filter { it.name.contains(query) || it.address.contains(query) }

    fun computeShippingFee(
        orderLines: List<OrderLine>,
        shippingDestination: ShippingDestination
    ): Float = ((orderLines.sumOf { it.count * it.snack.price } % 369) + (Math.random() * 369)).toFloat()

    fun defaultShippingDestination() = shippingDestinations.first()
}

val shippingDestinations = listOf(
    ShippingDestination(
        id = 1,
        name = "John's Bookstore",
        address = "123 Elm Street",
        city = "Springfield",
        state = "IL",
        zipCode = "62701",
        country = "USA"
    ),
    ShippingDestination(
        id = 2,
        name = "Smith Electronics",
        address = "456 Oak Avenue",
        city = "Metropolis",
        state = "NY",
        zipCode = "10001",
        country = "USA"
    ),
    ShippingDestination(
        id = 3,
        name = "Emily's Cafe",
        address = "789 Pine Road",
        city = "Gotham",
        state = "CA",
        zipCode = "90210",
        country = "USA"
    ),
    ShippingDestination(
        id = 4,
        name = "Brown's Grocery",
        address = "101 Birch Boulevard",
        city = "Star City",
        state = "TX",
        zipCode = "73301",
        country = "USA"
    ),
    ShippingDestination(
        id = 5,
        name = "Wilson's Hardware",
        address = "202 Cedar Lane",
        city = "Central City",
        state = "FL",
        zipCode = "32901",
        country = "USA"
    ),
    ShippingDestination(
        id = 6,
        name = "Davis Clothing",
        address = "303 Maple Drive",
        city = "Riverdale",
        state = "GA",
        zipCode = "30274",
        country = "USA"
    ),
    ShippingDestination(
        id = 7,
        name = "Martinez Pharmacy",
        address = "404 Spruce Street",
        city = "Smallville",
        state = "KS",
        zipCode = "66002",
        country = "USA"
    ),
    ShippingDestination(
        id = 8,
        name = "Lopez Bakery",
        address = "505 Willow Way",
        city = "Hill Valley",
        state = "CA",
        zipCode = "95420",
        country = "USA"
    ),
    ShippingDestination(
        id = 9,
        name = "Garcia Fitness",
        address = "606 Cherry Court",
        city = "Sunnydale",
        state = "CA",
        zipCode = "91006",
        country = "USA"
    ),
    ShippingDestination(
        id = 10,
        name = "Miller Art Gallery",
        address = "707 Aspen Circle",
        city = "Mystic Falls",
        state = "VA",
        zipCode = "22657",
        country = "USA"
    )
)


