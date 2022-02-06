import { RepoList } from "/src/views/RepoList.js";
import { ReposAPI } from "/src/apis/ReposAPI.js";
import { MetricsAPI } from "/src/apis/MetricsAPI.js";
import { Repos } from "/src/models/Repos.js";
import { Repo } from "/src/models/Repo.js";
import { Commits } from "/src/models/Commits.js";
import { CommitsAPI } from "/src/apis/CommitsAPI.js";
import { IssuesAPI } from "/src/apis/IssuesAPI.js";
import { CommitList } from "/src/views/CommitList.js";
import { AddRepo } from "/src/views/AddRepo.js";
import {
  RepoDetails,
  COMMIT,
  ISSUES,
  PULLS,
  AUTHOR_NAME,
  CREATED_ON_ASC,
  CREATED_ON_DESC,
} from "/src/views/RepoDetails.js";
import { Issues } from "/src/models/Issues.js";
import { IssuesList } from "/src/views/IssuesList.js";

export class RepoController {
  constructor() {
    this.repos = "";
    this.activeRepo = "";
    this.commits = "";
    this.issues;
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
        await this.loadCommits(
          this.activeRepo.getOwner(),
          this.activeRepo.getName()
        );
        break;
      case ISSUES:
        await this.loadIssues(
          this.activeRepo.getOwner(),
          this.activeRepo.getName()
        );
        break;
      case PULLS:
        break;
      default:
        break;
    }
  }

  async loadCommits(owner, name) {
    const commitsJSON = await this.getCommits(owner, name);
    this.commits.setCommits(commitsJSON);
  }

  async loadIssues(owner, name) {
    const issuesJSON = await this.getIssues(owner, name);
    this.issues.setIssues(issuesJSON);
  }

  async selectRepo(owner, name) {
    const metricsJSON = await this.getMetrics(owner, name);
    this.activeRepo.setRepo({
      owner: owner,
      name: name,
      healthPercentage: metricsJSON.healthPercentage,
    });
  }

  search(selected, keyword) {
    switch (selected) {
      case COMMIT:
        this.commits.search(keyword);
        break;
      case ISSUES:
        this.issues.search(keyword);
      default:
        break;
    }
    
  }

  sort(selected, type) {
    switch (selected) {
      case COMMIT:
        this.callSort(this.commits, type);
        break;
      case ISSUES:
        this.callSort(this.issues, type);
      default:
        break;
    }
  }

  callSort(callee, type) {
    
    switch (type) {
      case AUTHOR_NAME:
        callee.sortByAuthorName();
        break;
      case CREATED_ON_ASC:
        callee.sortByCreatedOn(0);
        break;
      case CREATED_ON_DESC:
        callee.sortByCreatedOn(1);
        break;
      default:
        return;
    }
  }

  bindViewsToModels() {
    this.activeRepo = new Repo();
    this.commits = new Commits();
    this.issues = new Issues();

    this.activeRepo.subscribe(
      new RepoDetails({
        onRequestToggle: (requestType) => this.onRepoInfoSelect(requestType),
        onSearch: (selected, keyword) => this.search(selected, keyword),
        onSort: (selected, type) => this.sort(selected, type),
      })
    );

    this.repos.subscribe(
      new RepoList({
        onRepoSelect: (owner, name) => this.selectRepo(owner, name),
      })
    );

    this.commits.subscribe(new CommitList());
    this.issues.subscribe(new IssuesList());
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

    this.bindViewsToModels();
  }
}
