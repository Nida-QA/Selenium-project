package EnumFactory.Assingment;

public enum SwagLabCart {
    XPATH_REMOVE("//button[@class='btn btn_secondary btn_small cart_button']");

    private String pageVariables;

    private SwagLabCart(String pageVariables) {

        this.pageVariables = pageVariables;
    }

    public String getValue() {
        return this.pageVariables;
    }

    public String getPageVariables(){return this.pageVariables;}
}
