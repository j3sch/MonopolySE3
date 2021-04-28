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
  switch (props.condition) {
    case 'top':
      return <TopField {...props} />
    case 'bottom':
      return <BottomField {...props} />
    case 'left':
      return <LeftField {...props} />
    case 'right':
      return <RightField {...props} />
    case 'go':
      return <GoField {...props} />
    case 'jail':
      return <JailField {...props} />
    case 'free parking':
      return <FreeParkingField {...props} />
    case 'go to jail':
      return <GoToJailField {...props} />
    case 'chance-x':
      return <ChanceXField {...props} />
    case 'chance-y':
      return <ChanceYField {...props} />
  }
}
