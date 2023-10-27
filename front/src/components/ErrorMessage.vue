<template>
    <div id="message" class="error">
        <p>{{ statusSistema }}</p>
    </div>
</template>
<script>
    export default{
        name: 'ErrorMessage',
        props: {
            statusSistema: String
        }
    }
</script>