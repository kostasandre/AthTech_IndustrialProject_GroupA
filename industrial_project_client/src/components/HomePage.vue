<template>
  <v-content>
    <v-container>
      <v-row align="center" justify="start">
        <!-- <h1>Company: {{this.company.companyName}}</h1> -->
      </v-row>
      <v-tabs>
        <v-tab>My Requests</v-tab>
        <v-tab>Company</v-tab>

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
              <template v-slot:top>
                <v-toolbar flat color="white">
                  <template>
                    <v-btn
                      color="primary"
                      dark
                      class="mb-2"
                      @click="editItem(defaultItem)"
                    >New Request</v-btn>
                  </template>

                  <v-dialog v-model="dialog" max-width="500px">
                    <v-card>
                      <v-card-title>
                        <span class="headline">{{ formTitle }}</span>
                      </v-card-title>

                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12" sm="12" md="12">
                              <v-text-field disabled v-model="company.companyName" label="Company"></v-text-field>
                            </v-col>
                          </v-row>
                          <v-row>
                            <v-col cols="12" sm="6" md="6">
                              <v-text-field v-model="editedItem.supervisor" label="Supervisor"></v-text-field>
                            </v-col>
                            <!-- <v-col cols="12" sm="6" md="6">
                              <v-text-field v-model="editedItem.requestDate" label="Request date"></v-text-field>
                            </v-col>-->
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
              <template v-slot:item.action="{ item }">
                <v-icon small class="mr-2" @click="editItem(item)">edit</v-icon>
                <v-icon small @click="deleteItem(item)">delete</v-icon>
              </template>
            </v-data-table>
          </v-card>
        </v-tab-item>
        <v-tab-item>
          <v-card>
            <v-card-text>
              <v-form ref="form" v-model="valid" :lazy-validation="false">
                <v-text-field
                  v-model="company.companyName"
                  label="Επωνυμία Εταιρίας:"
                  name="companyName"
                  type="text"
                  :rules="nameRules"
                />
                <v-text-field
                  v-model="company.afm"
                  label="ΑΦΜ:"
                  name="afm"
                  type="text"
                  :rules="requiredRules"
                />
                <v-text-field
                  v-model="company.address"
                  label="Διεύθυνση:"
                  name="address"
                  type="text"
                />
                <v-text-field
                  v-model="company.phone"
                  id="phone"
                  label="Τηλέφωνο:"
                  name="phone"
                  type="text"
                  :rules="requiredRules"
                />
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-spacer />
              <v-btn @click="apply" color="primary">Apply</v-btn>
            </v-card-actions>
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
      company: {},
      user: {},
      snackbarMessage: "",
      snackbarColor: "",
      snackbar: false,
      dialog: false,
      formTitle: "",
      editedIndex: -1,
        valid: true,
      editedItem: {
        companyId: "",
        company: "",
        description: "",
        requestDate: "",
        supervisor: ""
      },
      defaultItem: {
        title: "",
        description: ""
      },
      search: "",
      headers: [
        // { text: "Id", value: "id" },
        { text: "Request date", value: "requestDate", sortable: true },
        { text: "Description", value: "description" },
        { text: "Supervisor", value: "supervisor", sortable: true },
        { text: "Request code", value: "requestCode", sortable: true },
        {
          text: "Request Expiration Date",
          value: "requestExpireDate",
          sortable: true
        },
        { text: "Status", value: "status", sortable: true }
      ],
      myRequests: [],
      allAds: [],
      nameRules: [
        v => !!v || "Required value!",
        v => (v && v.length <= 10) || "Name must be less than 10 characters"
      ],
      requiredRules: [v => !!v || "Required value!"]
    };
  },
  mounted() {
    if (this.$route.params && this.$route.params.company) {
      this.company = this.$route.params.company;
      this.editedItem.companyId = this.company.id;
      this.editedItem.company = this.company.companyName;
      this.myRequests = this.company.getRequests;
      this.myRequests.sort(function(a,b) {
          a = new Date(a.requestDate);
      b = new Date(b.requestDate);
      return a>b ? -1 : a<b ? 1 : 0;
      });
      this.myRequests.forEach(element => {
        var date = new Date(element.requestDate);
        element.company = this.company.companyName;
        element.address = this.company.address;
        element.requestCode = element.id;
        element.requestDate = element.requestDate
          ? `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()} ${date.getHours()}:${date.getMinutes()}:${date.getSeconds()}`
          : "";
        element.requestExpireDate = element.requestExpireDate
          ? new Date(element.requestExpireDate).toLocaleDateString("en-GB")
          : "";
      });
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
    } else {
      this.$router.push("/login");
    }
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
        this.formTitle =
          this.editedIndex === -1 ? "New request" : this.editedItem.description;
      }

      this.dialog = true;
    },
    // onTabChange() {
    //   this.getAllAds();
    // },
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
      this.editedItem.company_id = this.company.id;
      axios.post("http://localhost:8080/company/request", this.editedItem).then(
        result => {
          if (result.status === 200) {
            this.snackbarColor = "green";
            this.snackbarMessage = "Request was created!";
            this.snackbar = true;
            this.user = result.data;
          } else {
            this.snackbarColor = "red";
            this.snackbarMessage = "Ad was not created!";
            this.snackbar = true;
          }
        },
        error => {
          this.snackbarColor = "red";
          this.snackbarMessage = "Error: " + error.message;
          this.snackbar = true;
        }
      );
      this.close();
    },
    apply() {
      if (!this.$refs.form.validate()) {
          return          
        }
      axios
        .post("http://localhost:8080/company/update-company", {
          id: this.company.id,
          companyName: this.company.companyName,
          afm: this.company.afm,
          address: this.company.address,
          phone: this.company.phone
        })
        .then(
          result => {
            if (result.status === 200 && result.data) {
              this.snackbarColor = "green";
                  this.snackbarMessage = "Company was updated!";
                  this.snackbar = true;
            }
          },
          error => {
            this.snackbarColor = "red";
            this.snackbarText =
              "Error during registration! Error: " + error.message;
            this.snackbar = true;
          }
        );
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