<template>
    <div id="login_form">
       <div class="sub-container">
            <h1>LOGIN</h1>
            <form id="form_validation" @submit="sendValidationForm">
                <div class="message_span" hidden><p>{{ message }}</p></div>
                <div class="content-form">
                    <i class=" icons-put fa-solid fa-envelope"></i>
                    <input class="input-form" type="text" id="email" name="email" v-model="email" placeholder=" E-mail">
                </div>
                <div class="content-form">
                    <i class="icons-put fa-solid fa-lock"></i>
                    <input class="input-form" type="password" name="password" id="password" v-model="password" placeholder=" Password" >
                </div>
                <button id="btn-enter" type="submit">ENTER</button>                  
            </form>
            <div class="btn-container">
                    <p>Don't have a account click here:</p>
                    <router-link to="/register"><button class="btn-add">ADD USER</button></router-link>
            </div>
       </div>
    </div>
</template>

<script>

    export default{
        name:'LoginForm',
        data(){
            return{
                email: null,
                password: null,
                message: null
            }
        },
        methods:{      
            async sendValidationForm(e){
                e.preventDefault();
                console.log("Login enviado!!");

                const data = {
                    email: this.email,
                    password: this.password
                }
               // const valiJSON = JSON.stringify(data);
            }
        }
    }
</script>

<style scoped>
#login_form{
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
.icons-put {
    position: absolute;
    margin-top: 10px;
    margin-left: 10px;
    color: #FFAC33;
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
.btn-container{
    display: flex;
    flex-direction: column;
    align-items: center;
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
#form_validation{
    display: flex;
    flex-direction: column;
    align-items: center;
}
.btn-add{
    font-size: 1.2em;
    background-color: transparent;
    border: none;
    color: #FFFFFF;
    margin-top: 10px;
}
.btn-add:hover{
    color: #FFAC33;
}
.message_span{
    background-color: #F8D7DA;
    border: 1px solid rgb(158, 78, 78);
    padding: 10px;
    margin-bottom: 15px;
    border-radius: 5px;
    font-size: 0.9em;
    color: #721C24;
}

</style>