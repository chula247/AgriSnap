package com.chula.agrisnap.navigation

const val ROUT_SPLASH = "splash"
const val ROUT_HOME = "home"
const val ROUT_GRAIN = "grain"
const val ROUT_POST = "post"
const val ROUT_VEGETABLE = "vegetable"
const val ROUT_STATER = "stater"
const val ROUT_STATE = "state"
const val ROUT_CHAT = "chat"
const val ROUT_NOTIFICATION = "notification"
const val ROUT_PROFILE = "profile"
const val ROUT_FRUIT = "fruit"
const val ROUT_DAIRY = "dairy"
const val ROUT_POULTRY = "poultry"
const val ROUT_CART = "cart"
const val ROUT_CHATS= "chats"
const val ROUT_CHECK= "check"
const val ROUTE_PROMOTION = "promotion_screen"
const val ROUTE_OFFER = "offer_screen"
const val ROUT_PROFILE_EDIT = "profile_edit"
const val ROUT_PROFILE_EDIT_WITH_ARG = "profile_edit/{userId}"

const val VegetableList = "vegetable_list"
const val VegetableDetail = "vegetable_detail"
const val VegetableDetailWithArg = "vegetable_detail/{vegId}"

//Authentication
const val ROUT_REGISTER = "Register"
const val ROUT_LOGIN = "Login"


//Products

const val ROUT_ADD_PRODUCT = "add_product"
const val ROUT_PRODUCT_LIST = "product_list"
const val ROUT_EDIT_PRODUCT = "edit_product/{productId}"

// ✅ Helper function for navigation
fun editProductRoute(productId: Int) = "edit_product/$productId"


// Vegetables

const val ROUT_ADD_VEGETABLE = "add_vegetable"
const val ROUT_VEGETABLE_LIST = "vegetable_list"
const val ROUT_EDIT_VEGETABLE = "edit_vegetable/{vegetableId}"

// ✅ Helper function for navigation
fun editVegetableRoute(vegetableId: Int) = "edit_vegetable/$vegetableId"

// Promotion

const val ROUT_ADD_PROMOTION = "add_promotion"
const val ROUT_PROMOTION_LIST = "promotion_list"
const val ROUT_EDIT_PROMOTION = "edit_promotion/{promotionId}"

// ✅ Helper function for navigation
fun editPromotionRoute(promotionId: Int) = "edit_promotion/$promotionId"


// Fruit

const val ROUT_ADD_FRUIT = "add_fruit"
const val ROUT_FRUIT_LIST = "fruit_list"
const val ROUT_EDIT_FRUIT = "edit_fruit/{fruitId}"

// ✅ Helper function for navigation
fun editFruitRoute(fruitId: Int) = "edit_fruit/$fruitId"

// Dairy

const val ROUT_ADD_DAIRY = "add_dairy"
const val ROUT_DAIRY_LIST = "dairy_list"
const val ROUT_EDIT_DAIRY = "edit_dairy/{dairyId}"

// ✅ Helper function for navigation
fun editDairyRoute(dairyId: Int) = "edit_dairy/$dairyId"

// Grain

const val ROUT_ADD_GRAIN = "add_grain"
const val ROUT_GRAIN_LIST = "grain_list"
const val ROUT_EDIT_GRAIN = "edit_grain/{grainId}"

// ✅ Helper function for navigation
fun editGrainRoute(grainId: Int) = "edit_grain/$grainId"

// Offer

const val ROUT_ADD_OFFER = "add_offer"
const val ROUT_OFFER_LIST = "offer_list"
const val ROUT_EDIT_OFFER = "edit_offer/{offerId}"

// ✅ Helper function for navigation
fun editOfferRoute(offerId: Int) = "edit_offer/$offerId"


