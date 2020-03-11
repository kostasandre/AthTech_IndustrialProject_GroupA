<template>
<v-app id="inspire">
    <v-content>
      <v-container
        class="fill-height"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col
            cols="12"
            sm="8"
            md="4"
          >
            <v-card class="elevation-12">
              <v-toolbar
                color="primary"
                dark
                flat
              >
                <v-toolbar-title>Register form</v-toolbar-title>
                <v-spacer />
                
                
              </v-toolbar>
              <v-card-text>
                <v-form ref="form" v-model="valid" :lazy-validation="false">
                  <v-text-field
                    v-model="firstName"
                    label="First Name"
                    name="firstName"
                    type="text"
                    :rules="nameRules"
                  />
                  <v-text-field
                  v-model="lastName"
                    label="Last Name"
                    name="lastName"
                    type="text"
                    :rules="nameRules"
                  />
                  <v-text-field
                  v-model="email"
                    label="Email"
                    name="email"
                    type="text"
                    prepend-icon="email"
                    required
                    :rules="emailRules"
                  />

                  <v-text-field
                  v-model="password"
                    id="password"
                    label="Password"
                    name="password"
                    prepend-icon="lock"
                    type="password"
                    required
                    :rules="nameRules"
                  />
                  <v-text-field
                  v-model="age"
                    id="age"
                    label="Age"
                    name="age"
                    type="number"
                    value="20"
                    required
                  />
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer />               
                <v-btn @click="register" color="primary">Register</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import axios from "axios";
import router from "../router"
export default {
  name: 'RegisterPage',
  props: {
    msg: String
  },
  data: function() {
    return {
        firstName: '',
        lastName: '',
        email: '',
        password:'',
        age: 20,
        valid: true,
        emailRules: [
        v => !!v || 'Required value!',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      nameRules: [
        v => !!v || 'Required value!',
        v => (v && v.length <= 10) || 'Name must be less than 10 characters',
      ]
    };
  },
  methods:{
    register: function(){
      if (!this.$refs.form.validate()) {
          return          
        }
      if(this.firstName == '' || this.lastName == '' || this.email == '' || this.password == ''){
        window.alert('Required fields are missing!');
        return;
      }
       axios.post('http://localhost:8080/create', 
       {
            firstName: this.firstName,
            lastName: this.lastName, 
            email: this.email,
            password: this.password,
            age: this.age
        }
      ).then(result => {
          /* eslint-disable no-console */
      console.log(JSON.stringify(result.data));
      if(result.status === 200 && result.data.length > 0){
          window.alert("Successful registration please login");
        router.push({path: '/'})
        
      }
      }, error => {
        /* eslint-disable no-console */
        window.alert("Error during registration! Error: " + error.response.data)
      });
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
