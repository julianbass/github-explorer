import { RepoList } from "/src/components/RepoList.js";
import { ReposAPI } from "/src/apis/ReposAPI.js";
import { MetricsAPI } from "/src/apis/MetricsAPI.js";
import { Repos } from "/src/models/Repos.js";
import { Repo } from "/src/models/Repo.js";
import { Commits } from "/src/models/Commits.js";
import { CommitsAPI } from "/src/apis/CommitsAPI.js";
import { IssuesAPI } from "/src/apis/IssuesAPI.js";
import { CommitList } from "/src/components/CommitList.js";
import { AddRepo } from "/src/components/AddRepo.js";
import {
    RepoDetails,
    COMMIT,
    ISSUES,
    PULLS,
} from "/src/components/RepoDetails.js";
import { Issues } from "../models/Issues.js";
import { IssuesList } from "../components/IssuesList.js";

export class RepoController {
    constructor() {
        this.repos = "";
        this.activeRepo = "";
    }

    async getRepos() {
        const api = new ReposAPI();
        const repos = await api.getRepos();
        return repos;
    }

    async getMetrics(owner, name) {
        const api = new MetricsAPI(owner, name);
        const metrics = await api.getMetrics();
        return metrics;
    }

    async getCommits(owner, name) {
        const commitsAPI = new CommitsAPI(owner, name);
        const commits = await commitsAPI.getCommits();
        return commits;
    }

    async getIssues(owner, name) {
        const api = new IssuesAPI(owner, name);
        const issues = await api.getIssues();
        return issues;
    }

    async getPulls() {}

    async addRepo(owner, name) {
        const api = new ReposAPI();
        await api.createRepo(owner, name);
        this.repos.addRepo({
            owner: owner,
            repoName: name,
        });
        this.repos.notify();
    }

    async onRepoInfoSelect(requestType) {
        switch (requestType) {
            case COMMIT:
                await this.loadCommits(this.activeRepo.owner, this.activeRepo.name);
                break;
            case ISSUES:
                await this.loadIssues(this.activeRepo.owner, this.activeRepo.name);
                break;
            case PULLS:
                break;
            default:
                break;
        }
    }

    async loadCommits(owner, name) {
        const commitsJSON = await this.getCommits(owner, name);
        const commits = new Commits(commitsJSON);
        const commitList = new CommitList();
        commits.subscribe(commitList);
        commits.notify();
    }

    async loadIssues(owner, name) {
        const issuesJSON = await this.getIssues(owner, name);
        const issues = new Issues(issuesJSON);
        const issueList = new IssuesList();
        issues.subscribe(issueList);
        issues.notify();
    }

    async selectRepo(owner, name) {
        const metricsJSON = await this.getMetrics(owner, name);

        const repo = new Repo({
            owner: owner,
            name: name,
            healthPercentage: metricsJSON.healthPercentage,
        });
        this.activeRepo = {
            owner: owner,
            name: name,
        };
        const repoDetails = new RepoDetails({
            onRequestToggle: (requestType) => this.onRepoInfoSelect(requestType),
        });
        repo.subscribe(repoDetails);
        repo.notify();

    }

    async init() {
        const reposJSON = await this.getRepos();
        new AddRepo({
            onRepoAdd: (owner, name) => this.addRepo(owner, name),
        }).update();

        this.repos = new Repos(reposJSON);
        const repoList = new RepoList({
            onRepoSelect: (owner, name) => this.selectRepo(owner, name),
        });
        this.repos.subscribe(repoList);
        this.repos.notify();
    }
}