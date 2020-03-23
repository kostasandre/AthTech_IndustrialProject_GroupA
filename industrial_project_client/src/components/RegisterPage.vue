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
                    v-model="companyName"
                    label="Επωνυμία Εταιρίας:"
                    name="companyName"
                    type="text"
                    :rules="nameRules"
                  />
                  <v-text-field
                  v-model="activities"
                    label="Δραστηριότητα Εταιρίας:"
                    name="activities"
                    type="text"
                  />
                  <v-text-field
                  v-model="afm"
                    label="ΑΦΜ:"
                    name="afm"
                    type="text"
                    :rules="requiredRules"
                  />
                  <v-text-field
                  v-model="address"
                    label="Διεύθυνση:"
                    name="address"
                    type="text"
                  />
                  <v-text-field
                  v-model="city"
                    id="city"
                    label="Πόλη:"
                    name="city"
                    type="text"
                  />
                  <v-text-field
                  v-model="tk"
                    id="tk"
                    label="Τ.Κ:"
                    name="tk"
                    type="text"
                  />
                  <v-text-field
                  v-model="name"
                    id="name"
                    label="Όνομα Υπευθύνου:"
                    name="name"
                    type="text"
                    :rules="nameRules"
                  />
                  <v-text-field
                  v-model="surname"
                    id="surname"
                    label="Επίθετο Υπευθύνου:"
                    name="surname"
                    type="text"
                    :rules="requiredRules"
                  />
                  <v-text-field
                  v-model="phone"
                    id="phone"
                    label="Τηλέφωνο:"
                    name="phone"
                    type="text"
                    :rules="requiredRules"
                  />
                  <v-text-field
                  v-model="email"
                    id="email"
                    label="E-mail:"
                    name="email"
                    type="text"
                    :rules="emailRules"
                  />
                  <v-text-field
                  v-model="username"
                    id="username"
                    label="Username:"
                    name="username"
                    type="text"
                    :rules="requiredRules"
                  />
                  <v-text-field
                  v-model="password"
                    id="password"
                    label="Password:"
                    name="password"
                    type="password"
                    :rules="requiredRules"
                  />
                  <v-text-field
                  v-model="repassword"
                    id="repassword"
                    label="Repeat Password:"
                    name="repassword"
                    type="password"
                    :rules="requiredRules"
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
import router from "../router"
export default {
  name: 'RegisterPage',
  props: {
    msg: String
  },
  data: function() {
    return {
        snackbar: false,
        snackbarText: '',
        companyName:'',
        activities:'',
        afm:'',
        address:'',
        city:'',
        tk:'',
        name:'',
        surname:'',
        phone:'',
        email:'',
        username:'',
        password:'',
        repassword:'',
        valid: true,
        emailRules: [
        v => !!v || 'Required value!',
        v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      nameRules: [
        v => !!v || 'Required value!',
        v => (v && v.length <= 10) || 'Name must be less than 10 characters',
      ],
      requiredRules: [
        v => !!v || 'Required value!',
      ],
    };
  },
  methods:{
    register: function(){
      if (!this.$refs.form.validate()) {
          return          
        }
      if(this.password !== this.repassword){
        this.snackbarText = 'Invalid password. Password must be the same!';
        this.snackbar = true;
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
      if(result.status === 200 && result.data.length > 0){
          window.alert("Successful registration please login");
        router.push({path: '/'})
        
      }
      }, error => {
        this.snackbarText = 'Error during registration! Error: ' + error.message;
        this.snackbar = true;
      });
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
