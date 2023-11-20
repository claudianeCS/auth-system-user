<template>
    <div class="form-register">
        <div class="sub-container">
            <h1>REGISTER</h1>
            <form id="form_register" @submit="createANewUser">
                <div class="content-form">
                    <i class="icons-put fa-solid fa-user-plus"></i>
                    <input class="input-form" type="text" id="username" name="username" v-model="firstname" placeholder=" First name">
                </div>
                <div class="content-form">
                    <i class=" icons-put fa-solid fa-envelope"></i>
                    <input class="input-form" type="text" id="email" name="email" v-model="email" placeholder=" E-mail">
                </div>
                <div class="content-form">
                    <i class="icons-put fa-solid fa-lock"></i>
                    <input class="input-form invalid-password" type="password" id="password" name="passoword" v-model="password" placeholder=" Password" @change="onChangeInput(password)" required>
                </div>
                <div class="password-rules">
                    <i class="pointer fa-solid fa-circle fa-2xs"></i> One uppercase letter and 8 characters.
                </div>
                <div class="content-form">
                    <i class="icons-put fa-solid fa-lock"></i>
                    <input class="input-form" type="password" id="pass_def" name="pass_def" v-model="conf_password" placeholder=" Confirm password" required>
                </div>
                <button id="btn-enter" type="submit">ENTER</button>   
            </form>
        </div>
    </div>
</template>
<script>
import api from '@/api';
import router from '@/router';

    export default{
        name:'RegisterForm',
        data(){
            return{
                firstname: null,
                email: null,
                password: null,
                conf_password: null
            }
        },
        methods:{
            onChangeInput(password){
                // 8 caracteres 1 letra maiscula
                const passwordRegex = /^(?=.*[A-Z])[0-9a-zA-Z$*&@#]{8,}$/;
                if (!passwordRegex.test(password)) {
                console.log("Invalido");
                    var inputInvalid = document.querySelector('#password');
                    console.log(inputInvalid);
                    inputInvalid.style.border = "3px solid rgb(210, 63, 63)";
                    setTimeout(() =>{
                        inputInvalid.style.border = "none"
                        inputInvalid.value = ""
                    }, "900");
                    return;
                }    
            },
            verifyConfirmPassword(){
                if(this.password != this.conf_password){
                    var inputInvalid = document.querySelector('#pass_def');
                    //console.log(inputInvalid);
                    inputInvalid.style.border = "3px solid rgb(210, 63, 63)";
                    setTimeout(() =>{
                        inputInvalid.style.border = "none"
                        inputInvalid.value = ""
                    }, "900")
                    return;
                }
            },
            async createANewUser(e){
                e.preventDefault();
                this.verifyConfirmPassword();

                const data = {
                    firstname : this.firstname,
                    email: this.email,
                    password : this.password
                }

                if(data != null){
                    
                    const dataJSON = JSON.stringify(data)

                    api.post('/auth/register', dataJSON, {
                        headers: {
                            'Content-type': 'application/json',
                            'Access-Control-Allow-Origin': 'http://localhost:8080',
                            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
                        }
                    }).then((response) => {
                        if(response.status === 200){
                            router.push('/home')
                        }
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                }


                
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
.icons-put {
    position: absolute;
    margin-top: 10px;
    margin-left: 10px;
    color: #FFAC33;
}
.password-rules{
    margin-bottom: 10px;
}
.pointer{
    font-size: 0.5em;
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
    width: 330px;
    height: 3.5vh;
    margin-bottom: 20px;
    padding-left: 35px;
    border: none;
    border-radius: 15px;
    font-size: 1em;
}
.input-form:focus{
    outline: none;
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