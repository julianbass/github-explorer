import axios from "axios";

export async function getRepos() {
  const { data } = await axios.get("/api/repos");
  console.log(data);
  return data;
}
