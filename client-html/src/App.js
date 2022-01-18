// import { RepoList } from "/src/components/RepoList.js";
import { RepoList } from "/src/components/RepoList.js";
import { ReposAPI } from "/src/apis/ReposAPI.js";
import { Repos } from "/src/models/Repos.js";
import { Commits } from "/src/models/Commits.js";
import { CommitsAPI } from "/src/apis/CommitsAPI.js";
import { CommitList } from "/src/components/CommitList.js";
import { AddRepo } from "/src/components/AddRepo.js";
import { RepoController } from "/src/controllers/RepoController.js"

export function App() {
    async function main() {
        const repoController = new RepoController();
        repoController.init();
    }

    main();
}