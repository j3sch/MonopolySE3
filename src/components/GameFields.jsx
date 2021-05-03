import { Top as TopField } from '~/ui/GameFields/TopField'
import { Bottom as BottomField } from '~/ui/GameFields/Bottom'
import { Left as LeftField } from '~/ui/GameFields/LeftField'
import { Right as RightField } from '~/ui/GameFields/RightField'
import { GoField as GoField } from '~/ui/GameFields/GoField'
import { JailField as JailField } from '~/ui/GameFields/JailField'
import { FreeParkingField as FreeParkingField } from '~/ui/GameFields/FreeParkingField'
import { GoToJailField as GoToJailField } from '~/ui/GameFields/GoToJailField'
import { ChanceXField as ChanceXField } from '~/ui/GameFields/ChanceXField'
import { ChanceYField as ChanceYField } from '~/ui/GameFields/ChanceYField'

const gameField = {
  top: TopField,
  bottom: BottomField,
  left: LeftField,
  right: RightField,
  go: GoField,
  jail: JailField,
  freeParking: FreeParkingField,
  goToJail: GoToJailField,
  chanceX: ChanceXField,
  chanceY: ChanceYField,
}

export function GameField(props) {
  const GameField = gameField[props.condition]
  return <GameField {...props} />
}
