<template>
  <div class="home">
    <div class="img_container">
      <img class="image_index" src="../assets/Designsemnome1.png" alt="">
    </div>
    <div class="sub-home">
      <LoginForm/>
    </div>
      
  </div>
</template>

<script>
// @ is an alias to /src
import LoginForm from '@/components/LoginForm.vue';

  export default {
    name: 'Login',
    components: {
      LoginForm
    }
  }
</script>

<style scoped>
.home{
  display: flex;
  justify-content: space-between;

}
.sub-home{
  width: 50%;
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
