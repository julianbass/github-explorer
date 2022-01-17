/**
 *
 */
 export default class ApiWrapper {
   
    /**
     *
     * @param {*} endpoint
     * @return {Function}
     */
    async getRequest(endpoint) {
      
      return fetch(endpoint)
          .then((res) => res && res.json());
    }
  
    /**
     *
     * @param {*} endpoint
     * @param {*} data
     * @return {Function}
     */
    async postRequest(endpoint, data) {
      return fetch(endpoint, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: new Headers({
          'Content-Type': 'application/json',
        }),
      }).then((response) => response.json());
    }
  }
  
  