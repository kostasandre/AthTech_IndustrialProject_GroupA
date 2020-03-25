<template>
  <v-container>
    <v-tabs>
      <!-- <v-tab @change="onTabChange">Ads</v-tab> -->
      <v-tab>My Requests</v-tab>
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
      </v-tab-item> -->
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
            :items="myAds"
            :items-per-page="5"
            :search="search"
            class="elevation-1"
          >
            <template v-slot:top>
              <v-toolbar flat color="white">
                <template>
                  <v-btn color="primary" dark class="mb-2" @click="editItem(defaultItem)">New Item</v-btn>
                </template>

                <v-dialog v-model="dialog" max-width="500px">
                  <v-card>
                    <v-card-title>
                      <span class="headline">{{ formTitle }}</span>
                    </v-card-title>

                    <v-card-text>
                      <v-container>
                        <v-row>
                          <v-col cols="12" sm="6" md="4">
                            <v-text-field v-model="editedItem.title" label="Title"></v-text-field>
                          </v-col>
                          <v-col cols="12" sm="6" md="4">
                            <v-text-field v-model="editedItem.description" label="Description"></v-text-field>
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
    </v-tabs>
    <br />
    <v-btn v-on:click="logout" color="primary">Log out</v-btn>
    <v-snackbar
      v-model="snackbar"
      :color="snackbarColor"
      :timeout='3000'
    >
      {{snackbarMessage}}
      <v-btn dark text @click="snackbar = false">Close</v-btn>
    </v-snackbar>
  </v-container>
</template>

<script>
import axios from "axios";
export default {
  name: "HomePage",
  data: function() {
    return {
      user: {},
      snackbarMessage: "",
      snackbarColor: "",
      snackbar: false,
      dialog: false,
      formTitle: "",
      editedIndex: -1,
      editedItem: {
        title: "",
        description: ""
      },
      defaultItem: {
        title: "",
        description: ""
      },
      search: "",
      headers: [
        // { text: "Id", value: "id" },
        { text: "Title", value: "title" },
        { text: "Description", value: "description" },
        { text: "Actions", value: "action", sortable: false }
      ],
      myAds: [],
      allAds: []
    };
  },
  mounted() {
    if (localStorage.name) {
      axios
        .get("http://localhost:8080/get", {
          params: {
            email: localStorage.name
          }
        })
        .then(
          result => {
            if (result.status === 200 && result.data.length === 0) {
              if (localStorage.name) {
                localStorage.removeItem("name");
              }
              this.$router.push("/login");
            } else if (result.status === 200 && result.data.length > 0) {
              this.user = result.data[0];
              if (result.data[0].advertises && result.data[0].advertises.length > 0) {                
                this.myAds = result.data[0].advertises;
              } else {
                this.myAds = [];
              }
              this.getAllAds();
            }
          },
          error => {
            this.snackbarColor = 'red';
            this.snackbarMessage = 'Error retreiving user reqeuests. Errr: ' + error.message;
            this.snackbar = true;
          }
        );
    } else {
      //this.$router.push("/login");
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
        this.editedIndex = this.myAds.indexOf(item);
        this.editedItem = Object.assign({}, item);
      }

      this.dialog = true;
    },
    onTabChange(){
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
        Object.assign(this.myAds[this.editedIndex], this.editedItem);
      } else {
        this.myAds.push(this.editedItem);
      }

      this.user.advertises = this.myAds;
      axios.post("http://localhost:8080/update", this.user).then(
        result => {
          if (result.status === 200) {
            this.snackbarColor = "green";
            this.snackbarMessage = "Ad was created!";
            this.snackbar = true;
            this.user = result.data;
            this.myAds = this.user.advertises;
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
    getAllAds(){
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
      )
    }
  }
};
</script>

<style scoped>
</style>