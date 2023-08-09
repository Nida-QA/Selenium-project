package EnumFactory.Assingment;

public enum SwagLabInventory {

    XPATH_ADD_TO_CART("//button[@class='btn btn_primary btn_small btn_inventory']"),
    XPATH_BASKET("//span[@class='shopping_cart_badge']");
    private String pageVariables;

    private SwagLabInventory(String pageVariables) {

        this.pageVariables = pageVariables;
    }

    public String getValue() {
        return this.pageVariables;
    }

    public String getPageVariables(){return this.pageVariables;}
}
