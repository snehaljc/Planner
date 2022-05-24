function toggleLoginSignup() {
    const login = document.getElementById('login');
    const loginState = login.style.display;
    const register = document.getElementById('register');
    console.log("Im on bitvh");
    if (loginState == 'block') {
        login.style.display = 'none';
        register.style.display = 'block';
    } else {
        register.style.display = 'none';
        login.style.display = 'block';
    }
}