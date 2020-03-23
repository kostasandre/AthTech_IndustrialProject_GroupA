<template>
  <v-app id="inspire">
    <v-content>
      <v-container class="fill-height" fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="4">
            <v-card class="elevation-12">
              <v-toolbar color="primary" dark flat>
                <v-toolbar-title>Login form</v-toolbar-title>
                <v-spacer />
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    v-model="email"
                    label="Email"
                    name="login"
                    type="text"
                  />

                  <v-text-field
                    v-model="password"
                    id="password"
                    label="Password"
                    name="password"
                    type="password"
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
    }),
  mounted() {
    if (localStorage.name) {
      this.$router.push({ path: "/" });
    }
  },
  methods: {
    login: function() {
      axios
        .get("http://localhost:8080/login", {
          params: {
            email: this.email,
            password: this.password
          }
        })
        .then(
          result => {
            if (result.status === 200 && result.data ) {              
              localStorage.name = result.data.email;              
              this.$router.push({ path: "/" });
              //window.alert("Logged in successfully!");
            }
          },
          error => {
            if(error.response && error.response.status === 404){
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
