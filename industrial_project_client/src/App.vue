<template>
  <v-app>
    <v-content style="background-color: #e0e0e0">
      <v-container
      >

     <v-row
          align="center"
          justify="start"
          
        >
        <v-col
        :cols="2">
          <h1>ESYD Portal</h1>
        </v-col>
        <v-col 
        :cols="8">
          <div> 
<v-img  src="./assets/logo.png" contain    
    height="100px"
    width="150px"></v-img>
          </div>
          
        </v-col>
        
        
     </v-row>
      </v-container>
    </v-content>
    <v-content style="background-color: #e0e0e0">
      <router-view/>
    </v-content>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    //
  }),
};
</script>
