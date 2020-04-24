<template>
  <v-content>
    <v-container>
      <v-row
     align="center"
          justify="start">
          <h1>ESYD</h1>
     </v-row>
      <v-tabs>
        <!-- <v-tab @change="onTabChange">Ads</v-tab> -->
        <v-tab>All Requests</v-tab>
        <!-- <v-tab-item>
        <v-card>
          <v-card-title>
            <v-spacer></v-spacer>
            <v-text-field
              v-model="search"
              append-icon="search"
              label="Search"
              single-line
              hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
            :headers="headers"
            :items="allAds"
            :items-per-page="5"
            :search="search"
            class="elevation-1"
          >
          </v-data-table>
        </v-card>
        </v-tab-item>-->
        <v-tab-item>
          <v-card>
            <v-card-title>
              <v-spacer></v-spacer>
              <v-text-field
                v-model="search"
                append-icon="search"
                label="Search"
                single-line
                hide-details
              ></v-text-field>
            </v-card-title>
            <v-data-table
              :headers="headers"
              :items="myRequests"
              :items-per-page="5"
              :search="search"
              class="elevation-1"
            >
            <template v-slot:item.action="{ item }">
            <v-icon
                small
                class="mr-2"
                color="blue"
                @click="editItem(item)"
            >
                edit
            </v-icon>
            <v-icon
                small
                color="red"
                @click="deleteItem(item.id)"
            >
                delete
            </v-icon>
        </template>
              <template v-slot:top>
                <v-toolbar flat color="white">

                  <v-dialog v-model="dialog" max-width="500px">
                    <v-card>
                      <v-card-title>
                        <span class="headline">{{ formTitle }}</span>
                      </v-card-title>

                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12" sm="12" md="12">
                              <v-text-field v-model="editedItem.company" label="Company"></v-text-field>
                            </v-col>                            
                          </v-row>
                          <v-row>
                            <v-col cols="12" sm="6" md="6">
                              <v-text-field v-model="editedItem.supervisor" label="Supervisor"></v-text-field>
                            </v-col>
                            <!-- <v-col cols="12" sm="6" md="6">
                              <v-text-field v-model="editedItem.requestDate" label="Request date"></v-text-field>
                            </v-col> -->
                          </v-row>
                          <v-row>
                            <v-col cols="12" sm="12" md="12">
                              <v-textarea
                              v-model="editedItem.description"
                              name="Description"
                              label="Description"
                            ></v-textarea>
                            </v-col>
                            
                          </v-row>
                        </v-container>
                      </v-card-text>

                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                        <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-toolbar>
              </template>
              <!-- <template v-slot:item.action="{ item }">
              <v-icon small class="mr-2" @click="editItem(item)">edit</v-icon>
              <v-icon small @click="deleteItem(item)">delete</v-icon>
            </template> -->
            </v-data-table>
          </v-card>
        </v-tab-item>
      </v-tabs>
      <br />
      <v-btn v-on:click="logout" color="primary">Log out</v-btn>
      <v-snackbar v-model="snackbar" :color="snackbarColor" :timeout="3000">
        {{snackbarMessage}}
        <v-btn dark text @click="snackbar = false">Close</v-btn>
      </v-snackbar>
    </v-container>
  </v-content>
</template>

<script>
import axios from "axios";
export default {
  name: "HomePage",
  data: function() {
    return {
      status: "",
      company:{},
      user: {},
      snackbarMessage: "",
      snackbarColor: "",
      snackbar: false,
      dialog: false,
      formTitle: "",
      editedIndex: -1,
      editedItem: {
        companyId:"",
        company: "",
        description: "",
        requestDate:"",
        supervisor:""
      },
      defaultItem: {
        title: "",
        description: ""
      },
      search: "",
      headers: [
        // { text: "Id", value: "id" },
        { text: "Company", value: "companyName" },
        { text: "Address", value: "address", sortable: true },
        { text: "Request date", value: "requestDate", sortable: true },
        { text: "Description", value: "description" },
        { text: "Supervisor", value: "supervisor", sortable: true },
        { text: "Request code", value: "requestCode", sortable: true },
        { text: "Request expire date", value: "requestExpireDate", sortable: true },
        { text: "Status", value: "status", sortable: true },
        {text: 'Action', value: 'action'},
      ],
      myRequests: [],
      allAds: []
    };
  },
  mounted() {
      axios.get("http://localhost:8080/company/companies").then(
        result => {
          if (result.status === 200) {
              result.data.forEach(company => {
                  if(company.getRequests.length > 0){
                      company.getRequests.forEach(request => {
                          request.companyName = company.companyName;
                          request.address = company.address;
                          request.requestDate = request.requestDate ? new Date(request.requestDate).toLocaleDateString("en-GB") : '';
        request.requestExpireDate = request.requestExpireDate ? new Date(request.requestExpireDate).toLocaleDateString("en-GB") : '';
this.myRequests.push(request);
                      });
                  
                  }
              });
            // this.myRequests.forEach(element => {
            // element.company = this.company.companyName;
            // element.address = this.company.address;
            // element.requestCode = element.id;
            // element.requestDate = element.requestDate ? new Date(element.requestDate).toLocaleDateString("en-GB") : '';
            // element.requestExpireDate = element.requestExpireDate ? new Date(element.requestExpireDate).toLocaleDateString("en-GB") : '';
            // });
          } else {
            this.snackbarColor = "red";
            this.snackbarMessage = "Error retreiving all requests!";
            this.snackbar = true;
          }
        },
        error => {
          this.snackbarColor = "red";
          this.snackbarMessage = "Error: " + error.message;
          this.snackbar = true;
        }
      );
      
    //   axios
    //     .get("http://localhost:8080/get", {
    //       params: {
    //         email: localStorage.name
    //       }
    //     })
    //     .then(
    //       result => {
    //         if (result.status === 200 && result.data) {
    //           if (localStorage.name) {
    //             localStorage.removeItem("name");
    //           }
    //           this.$router.push("/login");
    //         } else if (result.status === 200 && result.data.length > 0) {
    //           this.user = result.data[0];
    //           if (
    //             result.data[0].advertises &&
    //             result.data[0].advertises.length > 0
    //           ) {
    //             this.myAds = result.data[0].advertises;
    //           } else {
    //             this.myAds = [];
    //           }
    //           this.getAllAds();
    //         }
    //       },
    //       error => {
    //         this.snackbarColor = "red";
    //         this.snackbarMessage =
    //           "Error retreiving user reqeuests. Errr: " + error.message;
    //         this.snackbar = true;
    //       }
    //     );
  },
  methods: {
    logout() {
      if (localStorage.name) {
        localStorage.removeItem("name");
      }

      this.$router.push("/login");
    },
    editItem(item) {
      if (item) {
        this.editedIndex = this.myRequests.indexOf(item);
        this.editedItem = Object.assign({}, item);
        this.formTitle = this.editedIndex === -1 ? "New request" : this.editedItem.description;
      }

      this.dialog = true;
    },
    onTabChange() {
      this.getAllAds();
    },
    deleteItem(item) {
      if (item) {
        if (confirm("Do you really want to delete this record?")) {
          const index = this.myAds.indexOf(item);
          axios
            .get("http://localhost:8080/deleteAd", {
              params: {
                id: item.id
              }
            })
            .then(
              result => {
                if (result.status === 200) {
                  this.myAds.splice(index, 1);
                  this.snackbarColor = "green";
                  this.snackbarMessage = "Ad was deleted!";
                  this.snackbar = true;
                }
              },
              error => {
                this.snackbarColor = "red";
                this.snackbarMessage = "Error deleting Ad: " + error.message;
                this.snackbar = true;
              }
            );
        }
      }
    },
    close() {
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },

    save() {
      if (this.editedIndex > -1) {
        Object.assign(this.myRequests[this.editedIndex], this.editedItem);
      } else {
        this.myRequests.push(this.editedItem);
      }
      delete this.company.getRequests;
      delete this.company.createdAt;
      this.editedItem.company = this.company;
      axios.post("http://localhost:8080/company/request", this.editedItem).then(
        result => {
          if (result.status === 200) {
            this.snackbarColor = "green";
            this.snackbarMessage = "Request was created!";
            this.snackbar = true;
            this.user = result.data;
            this.myRequests.push(this.editedItem);
          } else {
            this.snackbarColor = "red";
            this.snackbarMessage = "Ad was not created!";
            this.snackbar = true;
          }
        },
        error => {
          this.myRequests.splice(this.editedIndex, 1);
          this.snackbarColor = "red";
          this.snackbarMessage = "Error: " + error.message;
          this.snackbar = true;
        }
      );
      this.close();
    },
    getAllAds() {
      axios.get("http://localhost:8080/getAllAds").then(
        result => {
          if (result.status === 200 && result.data.length === 0) {
            this.allAds = [];
          } else if (result.status === 200 && result.data.length > 0) {
            this.allAds = result.data;
          }
        },
        error => {
          /* eslint-disable no-console */
          console.log(error.message);
        }
      );
    }
  }
};
</script>

<style scoped>
</style>