<template>
  <v-app style="background-color: #e0e0e0" id="inspire">
    <v-content>
      <v-container>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-12">
              <v-toolbar color="primary" dark flat>
                <v-toolbar-title>Login form</v-toolbar-title>
                <v-spacer />
              </v-toolbar>
              <v-card-text>
                <v-form ref="form" v-model="valid" :lazy-validation="false">
                  <v-text-field
                    v-model="email"
                    label="Email"
                    name="login"
                    type="text"
                    :rules="requiredRules"
                  />

                  <v-text-field
                    v-model="password"
                    id="password"
                    label="Password"
                    name="password"
                    type="password"
                    :rules="requiredRules"
                  />
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer />
                <v-btn v-on:click="login" color="primary">Login</v-btn>
                <v-btn v-on:click="register" color="primary">Register</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
    <v-snackbar
      v-model="snackbar"
      :color="'red'"
      :timeout='3000'
    >
      {{ snackbarText }}
      <v-btn
        dark
        text
        @click="snackbar = false"
      >
        Close
      </v-btn>
    </v-snackbar>
  </v-app>
  
</template>

<script>
import axios from "axios";
export default {
  name: "LoginPage",
  props: {
    msg: String
  },
  data: () => ({
      snackbar: false,
      email: "",
      password: "",
      snackbarText:"",
      valid: true,
      requiredRules: [
        v => !!v || 'Required value!',
      ],
    }),
  mounted() {
    if (localStorage.name) {
      this.$router.push({ path: "/" });
    }
  },
  methods: {
    login: function() {
      if (!this.$refs.form.validate()) {
          return          
        }
      axios
        .post("http://localhost:8080/company/login",
        { 
            email: this.email,
            password: this.password
          
        })
        .then(
          result => {
            if (result.status === 200 && result.data ) {              
              localStorage.name = result.data.email;              
              this.$router.push({ path: "/", params:result.data });
              //window.alert("Logged in successfully!");
            }
          },
          error => {
            if(error.response && error.response.status === 404){
              this.snackbarText = "Invalid email or password!"
              this.snackbar = true;
            }
            else{
              this.snackbarText = "Internal server error!"
              this.snackbar = true;
            }
            /* eslint-disable no-console */
            console.log(error.message);
          }
        );
    },

    register: function() {
      this.$router.push("/register");
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
