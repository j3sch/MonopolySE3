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

export function GameField(props) {
  let borderColor = 'border-black border-2'

  if (props.player1) {
    borderColor = 'border-red-500 border-4'
  }
  if (props.player2) {
    borderColor = 'border-blue-500 border-4'
  }
  if (props.player3) {
    borderColor = 'border-green-500 border-4'
  }
  if (props.player4) {
    borderColor = 'border-yellow-500 border-4'
  }

  switch (props.condition) {
    case 'top':
      return <TopField {...props} borderColor={borderColor} />
    case 'bottom':
      return <BottomField {...props} borderColor={borderColor} />
    case 'left':
      return <LeftField {...props} borderColor={borderColor} />
    case 'right':
      return <RightField {...props} borderColor={borderColor} />
    case 'go':
      return <GoField {...props} borderColor={borderColor} />
    case 'jail':
      return <JailField {...props} borderColor={borderColor} />
    case 'free parking':
      return <FreeParkingField {...props} borderColor={borderColor} />
    case 'go to jail':
      return <GoToJailField {...props} borderColor={borderColor} />
    case 'chance-x':
      return <ChanceXField {...props} borderColor={borderColor} />
    case 'chance-y':
      return <ChanceYField {...props} borderColor={borderColor} />
  }
}
