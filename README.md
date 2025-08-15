1. Search functionality for the different restaurants, food items.
2. Display restaurants/food items when customer performs search.
3. Cart functionality where customer is able to add various items to cart for a single restaurant only.
4. Checkout functionality where customer places the order for items and makes payment.
5. System supports various payment methods.
6. Customer able to see old transactions.
7. Proper inventory management for the food items for each restaurant.
8. Price management for the food items of the restaurants.
9. Delivery agent accepts the order and deliver it to customer from the restaurant.


OrderManager
    createOrder(String customerId, String resId, List<Pair<String, Integer>> foodItems)
    
    manageOrder(String customerId, String resId, List<Pair<String, Integer>> foodItems)
        createOrder(String customerId, String resId, List<Pair<String, Integer>> foodItems)


        prepareFood(String orderId, String resId, List<Pair<String, Integer> foodItems>)
    
    

DeliveryManager


RestaurantManager
    Map<String, RestaurantDetails>

    prepareFood(String orderId, String resId, List<Pair<String, Integer>> foodItems)

    RestaurantDetails GetRestaurant(String resId)

    notifyRestaurantForOrder(RestaurantOrderDetails resOrderDetails)

    





















SearchManager

    SearchRestaurant(String s)

    SearchDishServingRestaurants(String s)

    GetTopRestaurantResults(Location location)


RestaurantsManager

    GetTopRestaurantsOnLocation(Location l, Filter filter)

    GetDishServingRestaurants(Location l, Filter filter)

    GetRestaurant(String resId)


Restuarant
    String resId
    String resName
    String chainId
    Address address
    List<Menu> menu


Menu
    List<FoodItem> foodItems

FoodItem
    String foodDescription
    Double Price
    String foodItemId
    










        
        
















