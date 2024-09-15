import React, { useState, useEffect } from 'react';
import { updateGame } from '../../services/gameService';

const GameUpdateForm = ({ game, onUpdateSuccess}) => {
    const [formData, setFormData] = useState({
        name: '',
        description: '',
        price: '',
        image: '',
        quantity: '',
        releaseDate: '',
        gameType: 'BOARD',
        licenseKeys: '',
      });

      useEffect(() => {
        setFormData({
          id: game.id || '',
          name: game.name || '',
          description: game.description || '',
          price: game.price || '',
          image: game.image || '',
          quantity: game.quantity || '',
          releaseDate: game.releaseDate || '',
          gameType: game.gameType || '',
          licenseKeys: game.licenseKeys || '',
        });
      }, [game]);

      const [errors, setErrors] = useState({});
    
    
      const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData((prevData) => ({
          ...prevData,
          [name]: type === 'checkbox' ? (checked ? 'VIDEO' : 'BOARD') : value,
        }));
      };
    
     const validateForm = () => {
    let isValid = true;
    const newErrors = {};

    //checks if is not null
    if (!formData.name.trim()) {
      newErrors.name = 'Nah-uh, you need to add the games name';
      isValid = false;
    }

    if (!formData.price) {
      newErrors.price = 'The game cannot be for free:(';
      isValid = false;
    }

    if (!formData.quantity) {
      newErrors.quantity = 'Heyho, you should at least say how many games we have';
      isValid = false;
    }

    if (!formData.releaseDate) {
      newErrors.quantity = 'All games have to have a release date';
      isValid = false;
    }

    if (formData.gameType === 'VIDEO' && !formData.licenseKeys.trim()) {
      newErrors.licenseKeys = 'License keys are required for video games';
      isValid = false;
    }

    setErrors(newErrors);
    return isValid;
  };

      const handleUpdate = async (e) => {
        e.preventDefault();
  
        if (validateForm()) {
          try {
            const updatedGame = await updateGame(formData);
            onUpdateSuccess(updatedGame);
            console.log('Updating the game...')
          } catch (error) {
            console.log('Error updating game: ' + error);
          }
        } else {
          console.log('Form validation failed');
        }
      };

  return (
    <form className="form" onSubmit={handleUpdate}>
    <h1>Create game</h1>
      <div>
        <label>Name:</label>
        <input
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
        />
        {errors.name && <p className="error">{errors.name}</p>}
      </div>

      <div>
          <label>Game Type:</label>
          <input
            type="checkbox"
            name="gameType"
            checked={formData.gameType === 'VIDEO'}
            onChange={handleChange}
          /> Is Video Game
        </div>

      <div>
        <label>Description:</label>
        <textarea
          name="description"
          value={formData.description}
          onChange={handleChange}
        />
        {errors.description && <p className="error">{errors.description}</p>}
      </div>

      <div>
          <label>Release date:</label>
          <input
            type="date"
            name="releaseDate"
            value={formData.releaseDate}
            onChange={handleChange}
          />
          {errors.releaseDate && <p className="error">{errors.releaseDate}</p>}
        </div>

      <div>
        <label>Price:</label>
        <input
          type="number"
          name="price"
          value={formData.price}
          
          onChange={handleChange}
        />
        {errors.price && <p className="error">{errors.price}</p>}
      </div>

      <div>
        <label>Image:</label>
        <input
          type="text"
          name="image"
          value={formData.image}
          onChange={handleChange}
        />
      </div>

      <div>
        <label>Quantity:</label>
        <input
          type="number"
          name="quantity"
          value={formData.quantity}
          onChange={handleChange}
        />
        {errors.quantity && <p className="error">{errors.quantity}</p>}
      </div>
      <button type="submit">Update Game</button>
    </form>
  );
};

export default GameUpdateForm;
