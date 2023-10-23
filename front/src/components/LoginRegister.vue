<template>
    <div class="form-register">
        <div class="sub-container">
            <h1>REGISTER</h1>
            <form id="form_register" @submit="createANewUser">
                <div class="content-form">
                    <label for=""></label>
                    <input class="input-form" type="text" id="username" name="username" placeholder="*Username" required>
                </div>
                <div class="content-form">
                    <label for=""></label>
                    <input class="input-form" type="text" id="email" name="email" placeholder="*Email" required>
                </div>
                <div class="content-form">
                    <label for=""></label>
                    <input class="input-form" type="password" id="pass_conf" @change="onChangeInput()" name="pass_conf" placeholder="*Password" required>
                </div>
                <div class="content-form">
                    <label for=""></label>
                    <input class="input-form" type="password" id="pass_def" name="pass_def" placeholder="Confirm password">
                </div>
                <button id="btn-enter" type="submit">ENTER</button>   
            </form>
        </div>
    </div>
</template>
<script>

    export default{
        name:'LoginRegister',
        data(){
            return{
                username: null,
                email: null,
                password: null,
                conf_password: null
            }
        },
        methods:{
            onChangeInput(){
                // 8 caracteres 1 letra maiscula
                const passwordRegex = /^(?=.*[A-Z])[0-9a-zA-Z$*&@#]{8,}$/;
                if (passwordRegex.test(this.password)) {
                // Password is valid
                //show message
                console.log("Valido");
                } else {
                // Password is not valid
                console.log("Invalido");
                }
            },
            async createANewUser(e){

            }
        }
    }
</script>
<style scoped>
.form-register{
    background-color: #5CE1E6 ;
    width: 100%;
    height: 100vh;
    display: flex;
    justify-content: space-evenly;
    flex-direction: column;
    align-items: center;
}
h1{
    font-weight: 400;
    color: #FFFFFF;
    font-size: 3em;
}
.sub-container{
    height: 60vh;
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    align-items: center;
}
#form_register{
    display: flex;
    flex-direction: column;
    align-items: center;
}

.input-form{
    width: 325px;
    height: 3.5vh;
    margin-bottom: 20px;
    padding: 15px;
    border: none;
    border-radius: 15px;
    font-size: 1em;
}
#btn-enter{
    width: 150px;
    padding: 15px;
    font-size: 1.2em;
    border-radius: 10px;
    border: none;
    background-color: #FFFFFF;
    color: #5CE1E6;
    font-weight: 500;
    margin-top: 20px;
}
#btn-enter:hover{
    background-color: #FFAC33;
    color: #FFFFFF;
}
</style>