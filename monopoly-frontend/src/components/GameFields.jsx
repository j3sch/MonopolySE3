import React from 'react';
import PropTypes from 'prop-types';
import { Top as TopField } from '~/ui/GameFields/TopField';
import { Bottom as BottomField } from '~/ui/GameFields/Bottom';
import { Left as LeftField } from '~/ui/GameFields/LeftField';
import { Right as RightField } from '~/ui/GameFields/RightField';
import { GoField } from '~/ui/GameFields/GoField';
import { JailField } from '~/ui/GameFields/JailField';
import { FreeParkingField } from '~/ui/GameFields/FreeParkingField';
import { GoToJailField } from '~/ui/GameFields/GoToJailField';
import { ChanceXField } from '~/ui/GameFields/ChanceXField';
import { ChanceYField } from '~/ui/GameFields/ChanceYField';

const fields = {
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
};
export function GameField(props) {
	const { condition } = props;

	const Fields = fields[condition];
	return <Fields {...props} />;
};

GameField.propTypes = {
	condition: PropTypes.string,
};
