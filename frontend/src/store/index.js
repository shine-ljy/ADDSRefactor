import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    user: JSON.parse(sessionStorage.getItem("addsCurrentUserInfo")) || {},
    token: sessionStorage.getItem("addsCurrentUserToken") || "",
    sysData: JSON.parse(sessionStorage.getItem("addsSysData")) || {}
  },
  mutations: {
    saveUserInfo(state, userInfo) {
      state.user = userInfo;
      // console.log(state.user);
      // Save "userInfo" to sessionStorage
      sessionStorage.setItem("addsCurrentUserInfo", JSON.stringify(userInfo));
    },
    saveToken(state, token) {
      state.token = token;
      // console.log(state.token);
      // Save "token" to sessionStorage
      sessionStorage.setItem("addsCurrentUserToken", token);
    },
    saveSysData(state, data) {
      state.sysData = data;
      // console.log(state.token);
      // Save "data" to sessionStorage
      sessionStorage.setItem("addsSysData", JSON.stringify(data));
    },
    clearUserInfo(state) {
      state.user = {};
      state.token = "";
      state.sysData = {};
      // Remove "userInfo" from sessionStorage
      sessionStorage.removeItem("addsCurrentUserInfo");
      // Remove "token" from sessionStorage
      sessionStorage.removeItem("addsCurrentUserToken");
      // Remove "data" from sessionStorage
      sessionStorage.removeItem("addsSysData");
    },
  },
  actions: {
    loadData(context) {
      let models = [];
      let metrics = [];

      axios({
        method: 'get',
        url: '/modelCategory/modelName',
      }).then(res => {
        // console.log(res.data);
        for (let model in res.data) {
          if (res.data.hasOwnProperty(model)) {
            models[res.data[model].id] = res.data[model].name;
          }
        }
      }).catch(error => {
        console.log(error);
      });

      axios({
        method: 'get',
        url: '/modelMetric',
      }).then(res => {
        // console.log(res.data);
        for (let metric in res.data) {
          if (res.data.hasOwnProperty(metric)) {
            metrics[res.data[metric].id] = res.data[metric].metricName;
          }
        }
      }).catch(error => {
        console.log(error);
      });

      context.commit('saveSysData', {
        models: models,
        metrics: metrics
      });
    }
  }
});

export default store;
