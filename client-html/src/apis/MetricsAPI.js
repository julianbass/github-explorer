import ApiWrapper from "/src/apis/APIWrapper.js";

export class MetricsAPI extends ApiWrapper {
  constructor(owner, repo) {
    super();
    this.owner = owner;
    this.repo = repo;
  }

  async getMetrics() {
    return this.getRequest(`/api/repos/${this.owner}/${this.repo}/metrics`);
  }


}
