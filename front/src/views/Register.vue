<template>
  <div class="register">
    <div class="img_container">
      <img class="image_index" src="../assets/Designsemnome1.png" alt="">
    </div>
    <div class="sub-register">
      <RegisterForm/>
    </div>

  </div>

</template>
<script>
import RegisterForm from '@/components/RegisterForm.vue'


  export default{
    name: 'Register',
    components:{
      RegisterForm
    }
  }
</script>
<style scoped>
.register{
  display: flex;
  justify-content: space-between;
}

.sub-register{
  width: 55%;
  height: 100vh;
}
.img_container{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  align-content: center;
  width: 50%;
}
.image_index{
  width: 50%;
}

</style>