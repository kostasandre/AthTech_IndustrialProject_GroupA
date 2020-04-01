<template>
<v-app style="background-color: #e0e0e0" id="inspire">
    <v-content>
      <v-container
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
                <v-btn @click="register" color="primary">Submit</v-btn>
                <v-btn v-on:click="login" color="primary">Login</v-btn>
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
        afm:'',
        address:'',
        phone:'',
        email:'',
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
       axios.post('http://localhost:8080/company/register', 
       {
            companyName:this.companyName,
        afm:this.afm,
        address:this.address,
        phone:this.phone,
        email:this.email,
        password:this.password
        }
      ).then(result => {
      if(result.status === 200  && result.data){
        this.$router.push({path: '/', name: 'home', params: {company: result.data}});
        
      }
      }, error => {
        this.snackbarText = 'Error during registration! Error: ' + error.message;
        this.snackbar = true;
      });
    },
    login: function(){
      router.push({path: '/login'});
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
