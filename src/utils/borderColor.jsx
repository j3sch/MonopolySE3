export const borderColor = (id) => {
  let player1 = 6
  let player2 = 21
  let player3 = 2
  let player4 = 13
  let borderColor
  ;(id === player1 && (borderColor = 'border-red-500 border-4')) ||
    (id === player2 && (borderColor = 'border-blue-500 border-4')) ||
    (id === player3 && (borderColor = 'border-green-500 border-4')) ||
    (id === player4 && (borderColor = 'border-yellow-500 border-4')) ||
    (borderColor = 'border-black border-2')

  return borderColor
}
