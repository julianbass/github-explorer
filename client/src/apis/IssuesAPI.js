import ApiWrapper from "/src/apis/APIWrapper.js";

export class IssuesAPI extends ApiWrapper {
    constructor(owner, repo) {
        super();
        this.owner = owner;
        this.repo = repo;
    }

    async getIssues() {
        return this.getRequest(`/api/repos/${this.owner}/${this.repo}/issues`);
    }


}