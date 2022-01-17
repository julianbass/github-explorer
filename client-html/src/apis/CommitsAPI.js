import ApiWrapper from "/src/apis/APIWrapper.js";

export class CommitsAPI extends ApiWrapper {
  constructor(owner, repo) {
    super();
    this.owner = owner;
    this.repo = repo;
  }

  async getCommits() {
    return this.getRequest(`/api/repos/${this.owner}/${this.repo}/commits`);
  }

  async getCommit(sha) {
      return this.getRequest(`/api/repos/${this.owner}/${this.repo}/commits${sha}`);
  }

}
