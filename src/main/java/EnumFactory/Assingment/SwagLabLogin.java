package EnumFactory.Assingment;

public enum SwagLabLogin {
    XPATH_USER_NAME("//input[@id='user-name']"),
    XPATH_PASSWORD("//input[@id='password']"),
    XPATH_LOGIN_BUTTON("//input[@id='login-button']");

    private String pageVariables;

    private SwagLabLogin(String pageVariables) {

        this.pageVariables = pageVariables;
    }

    public String getValue() {
        return this.pageVariables;
    }

    public String getPageVariables(){return this.pageVariables;}
}
