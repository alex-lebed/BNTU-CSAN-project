export const PlayerService = {
  getWinners,
};

function getWinners() {
  //TODO REST request
  return new Promise((resolve, reject) =>
    resolve({
      data: [
        { id: 2, name: "Alex", winsAmount: 3 },
        { id: 5, name: "Misha", winsAmount: 2 },
      ],
    })
  );
}
