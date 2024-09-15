import React, { useState } from 'react';
import axios from 'axios';

const PurchaseModal = ({ game, onClose, userId }) => {
  const [quantity, setQuantity] = useState(1);
  const [notes, setNotes] = useState('');

  const handleAccept = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/orders/add', null, {
        params: {
          gameId: game.id,
          quantity,
          orderComment: notes,
          userId
        }
      });
      console.log(response.data);
      onClose();
    } catch (error) {
      console.error('Error adding game to order: ', error);
    }
  };

  return (
    <div className="modal">
      <h2>Dane zakupu gry {game.name}</h2>
      <label>
        Ilość:
        <input
          type="number"
          value={quantity}
          onChange={(e) => setQuantity(e.target.value)}
          min="1"
        />
      </label>
      <label>
        Uwagi do zakupu:
        <textarea
          value={notes}
          onChange={(e) => setNotes(e.target.value)}
        />
      </label>
      <button onClick={onClose}>Anuluj</button>
      <button onClick={handleAccept}>Akceptuj</button>
    </div>
  );
};

export default PurchaseModal;
